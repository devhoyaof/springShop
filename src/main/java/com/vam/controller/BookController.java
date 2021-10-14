package com.vam.controller;

import com.vam.mapper.AttachMapper;
import com.vam.model.AttachImageVO;
import com.vam.model.BookVO;
import com.vam.model.Criteria;
import com.vam.model.PageDTO;
import com.vam.service.AttachService;
import com.vam.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private AttachService attachService;

	@Autowired
	private BookService bookService;

	@Autowired
	private AttachMapper attachMapper;


	//메인 페이지 이동
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public void mainPageGET(Model model) {

		logger.info("메인 페이지 진입");

		model.addAttribute("cate1", bookService.getCateCode1());
		model.addAttribute("cate2", bookService.getCateCode2());

	}

	// 이미지 출력
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName) {

		logger.info("d:\\upload\\" + fileName);

		File file = new File("d:\\upload\\" + fileName);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Context-type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

	/* 이미지 정보 반환 */
	@GetMapping(value="getAttachList")
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId) {

		logger.info("getAttachList............" + bookId);

		return new ResponseEntity<List<AttachImageVO>>(attachMapper.getAttachList(bookId), HttpStatus.OK);

	}

	/* 상품 검색 */
	@GetMapping(value="search")
	public String searchGoodsGET(Criteria cri, Model model) {

		logger.info("cri : " + cri);

		List<BookVO> list = bookService.getGoodsList(cri);
		logger.info("pre list : " + list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list : " + list);
		} else {
			model.addAttribute("listcheck", "empty");

			return "search";
		}

		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));

		String[] typeArr = cri.getType().split("");

		for(String s : typeArr) {
			if (s.equals("T") || s.equals("A")) {
				model.addAttribute("filter_info", bookService.getCateInfoList(cri));
			}
		}

		return "search";
	}

}
