package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.CategoryId;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.Optional;

import static com.apboutos.moneytrackapi.model.Entry.Type.Expense;
import static java.sql.Timestamp.from;
import static java.time.Instant.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SuppressWarnings("OptionalGetWithoutIsPresent")
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
        Category testCategory = categoryRepository.save(new Category(new CategoryId("Test Category", Entry.Type.Income)));
        User testUser = userRepository.save(new User("testUser@test.com","000000000000000000000000000000000000000000000000000000000000"));
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
    void update() {

        //given
        Category testCategory = categoryRepository.save(new Category(new CategoryId("Test Category", Entry.Type.Income)));
        User testUser = userRepository.save(new User("testUser@test.com","000000000000000000000000000000000000000000000000000000000000"));
        Entry testEntry = new Entry("testEntry",testUser, Entry.Type.Income,
                testCategory, "test",
                0.0, new Date(343), from(now()), false);

        entryRepositoryUnderTest.save(testEntry);

        //when
        entryRepositoryUnderTest.update("testEntry",testUser, Expense,testCategory,"testUpdated",1.0,new Date(346), from(now()),true);
        Optional<Entry> actual = entryRepositoryUnderTest.findEntryById("testEntry");

        //then
        assertThat(actual.get().getUsername()).isEqualTo(testUser);
        assertThat(actual.get().getType()).isEqualTo(Expense);
        assertThat(actual.get().getCategory()).isEqualTo(testCategory);
        assertThat(actual.get().getDescription()).isEqualTo("testUpdated");
        assertThat(actual.get().getAmount()).isEqualTo(1.0);
        assertThat(actual.get().getIsDeleted()).isEqualTo(true);
    }
}