<script setup>

// 반응형 상태
import {reactive} from "vue";
import Coupon from "@/views/Coupon.vue";
import {getCoupons} from "@/services/couponService.js";

const state = reactive({
  coupons: []
});

(async function onCreated() {
  const res = await getCoupons();

  if(res.status === 200){
    state.coupons = res.data;
  }
})();

</script>

<template>
  <div class="coupons">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row row-cols-1 row-cols-lg-2 row-cols-xl-3 g-3">
          <div class="col" v-for="coupon in state.coupons">
            <Coupon :coupon="coupon"/>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>