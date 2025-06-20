import axios from "axios";

// 장바구니 상품 목록 조회
export const getItems = () => {
    return axios.get("/v1/api/cart/items")
}