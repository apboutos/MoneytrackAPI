package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@DataJpaTest
class EntryRepositoryTest {

    @Autowired
    private EntryRepository entryRepositoryUnderTest;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findEntryById() {

        //given

        User testUser = userRepository.save(new User("testUser@test.com","000000000000000000000000000000000000000000000000000000000000"));
        Category testCategory = categoryRepository.save(new Category("Test Category", Entry.Type.Income,testUser));
        Entry testEntry = new Entry("testEntry",testUser, Entry.Type.Income,
                testCategory, "test",
                0.0, new Date(343), from(now()), false);

        entryRepositoryUnderTest.save(testEntry);

        //when
        Optional<Entry> actual = entryRepositoryUnderTest.findEntryById("testEntry");
        Optional<Entry> actual2 = entryRepositoryUnderTest.findEntryById("testEntry2");

        //then
        assertThat(actual).contains(testEntry);
        assertThat(actual2).isEmpty();

    }

    @Test
    void findEntriesByUsernameAndLastUpdateAfter(){

        //given
        User testUser = userRepository.save(new User("testUser@test.com","000000000000000000000000000000000000000000000000000000000000"));
        Category testCategory = categoryRepository.save(new Category("Test Category", Entry.Type.Income,testUser));

        Timestamp lastUpdate1 = Timestamp.valueOf("2021-05-23 15:29:30");
        Timestamp lastUpdate2 = Timestamp.valueOf("2021-05-23 15:29:31");
        Timestamp lastUpdate3 = Timestamp.valueOf("2021-05-23 15:29:32");
        Timestamp lastPullRequestBeforeAll = Timestamp.valueOf("2021-05-23 15:29:29");
        Timestamp lastPullRequestAfterAll = Timestamp.valueOf("2021-05-23 15:29:33");
        Timestamp lastPullRequestAfterFirst = Timestamp.valueOf("2021-05-23 15:29:30");

        Entry entry1 = new Entry("testEntry1",testUser, Entry.Type.Income, testCategory, "test", 0.0, new Date(343), lastUpdate1, false);
        Entry entry2 = new Entry("testEntry2",testUser, Entry.Type.Income, testCategory, "test", 0.0, new Date(343), lastUpdate2, false);
        Entry entry3 = new Entry("testEntry3",testUser, Entry.Type.Income, testCategory, "test", 0.0, new Date(343), lastUpdate3, false);

        entryRepositoryUnderTest.save(entry1);
        entryRepositoryUnderTest.save(entry2);
        entryRepositoryUnderTest.save(entry3);

        //when
        List<Entry> actualEntriesBeforeAll = entryRepositoryUnderTest.findEntriesByUsernameAndLastUpdateAfter(testUser,lastPullRequestBeforeAll);
        List<Entry> actualEntriesAfterAll = entryRepositoryUnderTest.findEntriesByUsernameAndLastUpdateAfter(testUser,lastPullRequestAfterAll);
        List<Entry> actualEntriesAfterFirst = entryRepositoryUnderTest.findEntriesByUsernameAndLastUpdateAfter(testUser,lastPullRequestAfterFirst);

        //then
        assertThat(actualEntriesBeforeAll.size()).isEqualTo(3);
        assertThat(actualEntriesBeforeAll.get(0)).isEqualTo(entry1);
        assertThat(actualEntriesBeforeAll.get(1)).isEqualTo(entry2);
        assertThat(actualEntriesBeforeAll.get(2)).isEqualTo(entry3);
        assertThat(actualEntriesAfterAll.size()).isEqualTo(0);
        assertThat(actualEntriesAfterFirst.size()).isEqualTo(2);
        assertThat(actualEntriesAfterFirst.get(0)).isEqualTo(entry2);
        assertThat(actualEntriesAfterFirst.get(1)).isEqualTo(entry3);
    }

}