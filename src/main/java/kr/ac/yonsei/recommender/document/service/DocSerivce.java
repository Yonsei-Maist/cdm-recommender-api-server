/**
 * Save and load EMR documents
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
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

    /**
     * Save the EMR document
     * @param dto DocSaveRequestDto
     * @throws Exception all of error
     */
    @Transactional
    public void save(DocSaveRequestDto dto) throws Exception {
        docRepository.save(dto.toEntity());
    }

    /**
     * Get the EMR document
     * @param id
     * @return DocResponseDto
     * @throws Exception all of error
     */
    @Transactional(readOnly = true)
    public DocResponseDto findById(String id) throws Exception {
        Doc entity = docRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id="+id));

        return new DocResponseDto(entity);
    }

    /**
     * Get a list of EMR documents
     * @param userId
     * @return List<DocListResponseDto>
     * @throws Exception all of error
     */
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
