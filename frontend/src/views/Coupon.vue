<script setup>
import { useRouter } from "vue-router";
import { useAccountStore } from "@/stores/account.js";

// props 정의
const props = defineProps({
  coupon: {
    code: String,
    name: String,
    discountType: String,
    discountValue: Number,
    discountPer: Number,
    validFrom: Date,
    validTo: Date,
  }
});

const coupon = props.coupon;
const router = useRouter();
const accountStore = useAccountStore();

// 날짜 포맷 함수
function formatDate(date) {
  const d = new Date(date);
  const yyyy = d.getFullYear();
  const mm = String(d.getMonth() + 1).padStart(2, '0');
  const dd = String(d.getDate()).padStart(2, '0');
  return `${yyyy}.${mm}.${dd}`;
}

// 쿠폰 발급
const issue = async () => {
  if (!accountStore.loggedIn) {
    if (window.confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?")) {
      await router.push("/login");
    }
    return;
  }

  // 여기에 발급 API 호출 또는 처리 로직 추가
  alert(`쿠폰 '${coupon.name}'이(가) 발급되었습니다.`);
};
</script>

<template>
  <div class="coupon shadow-sm">
    <div class="coupon-body p-3">
      <h5 class="coupon-title mb-2">{{ coupon.name }}</h5>
      <p class="text-muted mb-1">코드: {{ coupon.code }}</p>
      <p class="mb-1">
        할인:
        <template v-if="coupon.discountType === 'FIXED'">
          {{ coupon.discountValue.toLocaleString() }}원
        </template>
        <template v-else-if="coupon.discountType === 'PERCENT'">
          {{ coupon.discountPer }}%
        </template>
      </p>
      <p class="mb-2">
        유효기간: {{ formatDate(coupon.validFrom) }} ~ {{ formatDate(coupon.validTo) }}
      </p>
      <div class="d-flex justify-content-end">
        <button class="btn btn-primary btn-sm" @click="issue()">쿠폰 발급</button>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.coupon {
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;

  .coupon-body {
    .coupon-title {
      font-weight: bold;
    }

    p {
      margin: 0;
      font-size: 0.95rem;
    }

    .btn {
      font-size: 0.85rem;
    }
  }
}
</style>
