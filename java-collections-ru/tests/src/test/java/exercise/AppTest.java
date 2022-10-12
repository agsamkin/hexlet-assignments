package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> elements1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedElements1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        int count1 = 3;
        assertThat(App.take(elements1, count1)).isEqualTo(expectedElements1);
        assertThat(App.take(elements1, count1).size()).isEqualTo(count1);

        List<Integer> elements2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedElements2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int count2 = 8;
        assertThat(App.take(elements2, count2)).isEqualTo(expectedElements2);
        assertThat(App.take(elements2, count2).size()).isEqualTo(elements2.size());
        // END
    }
}
