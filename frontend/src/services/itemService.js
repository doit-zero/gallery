import axios from "axios";
/**
 * 이 서비스는 백엔드 API를 호출해서 상품목록 데이터를 처리하는 기능을 제공함
 *
 * */
// 상품 목록 조회
export const getItems = () => {
    return axios.get("/v1/api/items").catch(e=>e.response);
}