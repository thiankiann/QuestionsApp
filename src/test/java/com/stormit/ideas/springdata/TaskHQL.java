package com.stormit.ideas.springdata;

import com.stormit.ideas.springdata.entitymanager.Note;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class TaskHQL {

    @Autowired
    private EntityManager entityManager;

    @ParameterizedTest
    @ValueSource(strings = {
            "select note from Note note",
            "select note from Note as note",
            "from Note note",
            "from Note"
    })
    void shouldSelectListIfObjects(String hql) {
        //given
        entityManager.persist(new Note(1, "Note 1", "Note 1"));
        entityManager.persist(new Note(2, "Note 2", "Note 2"));

        // when
        TypedQuery<Note> query = entityManager.createQuery(hql, Note.class);
        List<Note> resultList = query.getResultList();

        //then
        assertThat(resultList).hasSize(2);
        assertThat(resultList)
                .extracting(Note::getName)
                .containsExactlyInAnyOrder("Note 1", "Note 2");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "select note.name from Note note",
            "select name from Note note"
    })
    void shouldSelectSingleFieldFromObject(String hql) {
        //given
        entityManager.persist(new Note(1, "Note1", "Note 1"));
        entityManager.persist(new Note(2, "Note2", "Note 2"));

        //when
        TypedQuery<String> query = entityManager.createQuery(hql, String.class);
        List<String> resultList = query.getResultList();

        // then
        assertThat(resultList).hasSize(2);
        assertThat(resultList)
                .containsExactlyInAnyOrder("Note1", "Note2");
    }

    @ParameterizedTest
    @ValueSource( strings = {
            "select note.id, note.name, concat('Note', ' ', note.name) from Note note",
    })
    void shouldSelectCustomFields(String hql) {

    //given
        entityManager.persist(new Note(1, "Note1", "Note 1"));
    //when
        Query query = entityManager.createQuery(hql);
        List resultList = query.getResultList();
    //then
    assertThat(resultList).hasSize(1);
    Object[] object = (Object[]) resultList.get(0);

    assertThat(object[0]).isEqualTo(1);
    assertThat(object[1]).isEqualTo("Note1");
    assertThat(object[2]).isEqualTo("Note Note1");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "from Note note where id=1",
            "from Note note where note.name = 'Note1'",
            "from Note note where name like '%te1%'"
    })
    void shouldFilterObjects(String hql) {
        // given
        entityManager.persist(new Note(1, "Note1", "Note 1"));
        entityManager.persist(new Note(2, "Note2", "Note 2"));

        // when
        TypedQuery<Note> query = entityManager.createQuery(hql, Note.class);
        List<Note> resultList = query.getResultList();

        // then
        assertThat(resultList).hasSize(1);
        assertThat(resultList.get(0).getName()).isEqualTo("Note1");
    }

}