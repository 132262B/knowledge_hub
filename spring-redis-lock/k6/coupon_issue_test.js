import http from 'k6/http';
import { check } from 'k6';

export const options = {
  scenarios: {
    parallel_bulk: {
      executor: 'per-vu-iterations',
      vus: 100,
      iterations: 1,
      maxDuration: '100s',
      exec: 'parallelRequest',
    },
    final_single: {
      executor: 'per-vu-iterations',
      vus: 1,
      iterations: 1,
      startTime: '5s', // 병렬 요청 이후에 시작
      exec: 'finalRequest',
    },
  },
};

export function parallelRequest() {
  const res = http.patch('http://localhost:8080/api/v1/coupons/1/issue');
  console.log(`[병렬 요청] status=${res.status}, message=${res.body}`);
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}

export function finalRequest() {
  const res = http.patch('http://localhost:8080/api/v1/coupons/1/issue');
  console.log(`[마지막 요청] status=${res.status} | body=${res.body}`);
  check(res, {
    '마지막 요청은 500이어야 함': (r) => r.status === 500,
  });
}

// k6 run coupon_issue_test.js