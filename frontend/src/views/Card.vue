<script setup>
import {computed} from "vue";

// 프로퍼티 객체 defineProps는 컴포넌트가 생성될 때 가장 먼저 실행 setUp함수 실행 전 컴파일 타임에 처리된다
const props = defineProps({
  item: {
    id: Number,
    imgPath: String,
    name: String,
    price: Number,
    discountPer: Number
  }
});

// 상품 할인가
const computedItemDiscountPrice = computed(() => {
  return (props.item.price - (props.item.price * props.item.discountPer / 100)).toLocaleString() + '원';
});

// 장바구니에 상품 담기
const put = async () =>{
  window.alert("준비 중입니다.");
};
</script>

<template>
  <div class="card shadow-sm">
    <!-- 상품 사진-->
    <span class="img" :style="{backgroundImage: `url(${props.item.imgPath})`}" :aria-label="`싱픔 사진(${props.item.name})`"></span>
    <div class="card-body">
      <p class="card-text">
        <!--상품 이름-->
        <span class="me-2">{{ props.item.name }}</span>
        <!-- 상품 할인율 -->
        <span class="discount badge bg-danger">{{ props.item.discountPer }}</span>
      </p>
      <div class="d-flex justify-content-between align-ite-center">
        <button class="btn btn-primary btn-sm" @click="put()">장바구니 담기</button>
        <small class="price text-muted">{{ props.item.price.toLocaleString() }}원</small>
        <small class="real text-danger">{{ computedItemDiscountPrice }}</small>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
.card{
  .img{
    display: inline-block;
    width: 100%;
    height: 250px;
    background-size: cover;
    background-position: center;
  }

  .card-body .price {
    text-decoration: line-through;
  }
}
</style>