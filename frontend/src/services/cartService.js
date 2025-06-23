import httpRequester from "@/lib/httpRequester.js";
/**
 * 이 서비스는 백엔드 API를 호출해서 장바구니의 데이터를 처리하는 기능을 제공함
 *
 * */
// 장바구니 상품 목록 조회
export const getItems = () => {
    return httpRequester.get("/v1/api/cart/items").catch(e=>e.response);
}

// 장바구니 상품 추가
export const addItem = (itemId) => {
    return httpRequester.post("/v1/api/carts",{itemId}).catch(e=>e.response);
}

// 장바구니에서 상품 삭제
export const removeItem = (itemId) => {
    return httpRequester.delete(`/v1/api/cart/items/${itemId}`).catch(e=>e.response);
}