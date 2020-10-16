package kr.ac.yonsei.recommender.document.service;

import kr.ac.yonsei.recommender.document.dao.DocRepository;
import kr.ac.yonsei.recommender.document.domain.Doc;
import kr.ac.yonsei.recommender.document.dto.DocListResponseDto;
import kr.ac.yonsei.recommender.document.dto.DocResponseDto;
import kr.ac.yonsei.recommender.document.dto.DocSaveRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DocSerivce {

    @NonNull
    private final DocRepository docRepository;

    @Transactional
    public void save(DocSaveRequestDto docDto) throws Exception {
        docRepository.save(docDto.toEntity());
    }

    @Transactional(readOnly = true)
    public DocResponseDto findById(String id) throws Exception {
        Doc entity = docRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id="+id));

        return new DocResponseDto(entity);
    }

    @Transactional
    public List<DocListResponseDto> findAll(String userId) throws Exception {
        List<DocListResponseDto> docList = docRepository
                .findAllDescByUserId(userId)
                .stream()
                .map(DocListResponseDto::new)
                .collect(Collectors.toList());

        return docList;
    }

}
