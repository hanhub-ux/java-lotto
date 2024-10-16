package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {
    @Test
    @DisplayName("1등 2장 구매 당첨금")
    void getWinningAmount() {
        int[] lottoNumber = new int[]{1, 2, 3, 4, 5, 6};

        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumber);
        Lottos lottos = getLottos(Arrays.asList(lottoNumbers, lottoNumbers));
        assertThat(lottos.getWinningAmount(new Lotto(lottoNumbers))).isEqualTo(LottoRankingEnum.FIRST_PRIZE.getWinningAmount().multiply(BigDecimal.valueOf(2)));
    }

    @Test
    @DisplayName("꽝 당첨금")
    void getWinningAmount2() {
        LottoNumbers lottoNumbers = new LottoNumbers(new int[] {1,2,3,4,5,6});
        Lotto winningLotto = new Lotto(new LottoNumbers(new int[]{7,8,9,10,11,12}));
        Lottos lottos = getLottos(Arrays.asList(lottoNumbers));
        assertThat(lottos.getWinningAmount(winningLotto)).isEqualTo(LottoRankingEnum.LOSING_LOT.getWinningAmount());
    }

    @Test
    @DisplayName("1등 당첨 건수 2건")
    void getWinningResult() {
        LottoNumbers lottoNumbers = new LottoNumbers(new int[] {1,2,3,4,5,6});
        Lotto winningLotto = new Lotto(lottoNumbers);
        Lottos lottos = getLottos(Arrays.asList(lottoNumbers, lottoNumbers));

        assertThat(lottos.getWinningResult(winningLotto).keySet()).hasSize(1);
        assertThat(lottos.getWinningResult(winningLotto).get(LottoRankingEnum.FIRST_PRIZE)).isEqualTo(2);
    }

    @Test
    @DisplayName("3장 로또 구매 총금액")
    void getTotalPaymentAmount() {
        LottoNumbers lottoNumbers = new LottoNumbers(new int[]{1,2,3,4,5,6});
        Lottos lottos = new Lottos(Arrays.asList(lottoNumbers,lottoNumbers,lottoNumbers));

        assertThat(lottos.getTotalPaymentAmount()).isEqualTo(BigDecimal.valueOf(3000));

    }

    @Test
    void getSize() {
        LottoNumbers lottoNumbers = new LottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        Lottos lottos = new Lottos(Arrays.asList(lottoNumbers,lottoNumbers,lottoNumbers));

        assertThat(lottos.getSize()).isEqualTo(3);

    }

    private Lottos getLottos(List<LottoNumbers> lottoList) {
        return new Lottos(lottoList);
    }

}
