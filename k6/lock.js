import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 50,        // 동시에 50명 요청
  duration: '5s', // 5초 동안 부하
};

export default function () {
  const url = 'http://localhost:8080/tickets/lock/decrease?key=event&count=1';

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  let res = http.post(url, null, params);
  check(res, { 'status was 200': (r) => r.status === 200 });
  sleep(0.1);
}