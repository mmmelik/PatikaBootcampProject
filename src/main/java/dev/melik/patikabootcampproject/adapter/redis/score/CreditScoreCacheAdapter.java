package dev.melik.patikabootcampproject.adapter.redis.score;

import dev.melik.patikabootcampproject.domain.port.cache.CreditScoreCachePort;
import dev.melik.patikabootcampproject.domain.score.CreditScore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditScoreCacheAdapter implements CreditScoreCachePort {

    private final RedisTemplate<String, CreditScore> creditScoreRedisTemplate;

    @Override
    public Optional<CreditScore> getCreditScore(Long tckn) {
        CreditScore creditScore=creditScoreRedisTemplate.opsForValue().get("project:score:"+tckn);
        return Optional.ofNullable(creditScore);
    }

    @Override
    public void saveCreditScore(Long tckn, Integer score) {
        creditScoreRedisTemplate.opsForValue().set("project:score:"+tckn,CreditScore.builder().tckn(tckn).score(score).build(), Duration.ofDays(1));
    }
}
