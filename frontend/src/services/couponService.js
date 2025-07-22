import httpRequester from "@/lib/httpRequester.js";

/**
 * REST API 호출
 * 목적 : 발급 가능한 쿠폰 목록을 가져옴
 * */
export const getCoupons = () => {
    return httpRequester.get("/v1/api/coupons").catch(e=>e.response);
}

export const issueCoupon = (id) => {
    return httpRequester.post(`/v1/api/coupons/${id}`).catch(e=>e.response);
}