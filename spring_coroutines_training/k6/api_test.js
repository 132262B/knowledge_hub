import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 100, // 동시 사용자 수
    //duration: '1m', // 테스트 지속 시간
    iterations: 100,
};

export default function () {
    let res = http.get('http://localhost:8080/api/test'); // 여기에 테스트하고 싶은 URL을 입력하세요
    check(res, { 'status was 200': (r) => r.status == 200 });
    sleep(1);
};
