package lotto.domain;

import java.util.List;

public interface LottoNumberGenerator {
    public List<LottoNumbers> genLottoNumbers(int cnt);
}
