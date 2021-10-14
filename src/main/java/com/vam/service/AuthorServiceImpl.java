package com.vam.service;

import com.vam.mapper.AuthorMapper;
import com.vam.model.AuthorVO;
import com.vam.model.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

	private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

	@Autowired
	AuthorMapper authorMapper;

	// 작가 등록
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		authorMapper.authorEnroll(author);
	}

	// 작가 목록
	@Override
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception {

		logger.info("(service)authorGetList()........." + cri);

		return authorMapper.authorGetList(cri);
	}

	// 작가 총 수
	@Override
	public int authorGetTotal(Criteria cri) throws Exception {
		logger.info("(service)authorGetTotal()......." + cri);
		return authorMapper.authorGetTotal(cri);
	}

	// 작가 상세 페이지
	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		logger.info("authorGetDetail........." + authorId);
		return authorMapper.authorGetDetail(authorId);
	}

	// 작가 정보 수정
	@Override
	public int authorModify(AuthorVO author) throws Exception {
		logger.info("(service) authorModify........." + author);

		return authorMapper.authorModify(author);
	}

	/* 작가 정보 삭제 */
	@Override
	public int authorDelete(int authorId) {

		logger.info("authorDelete..........");

		return authorMapper.authorDelete(authorId);
	}
}