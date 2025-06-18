<script src="services/accountService.js"></script>
<script setup>
import {check} from "@/services/accountService.js";
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import {useAccountStore} from "@/stores/account.js";
import {watch} from "vue";
import {useRoute} from "vue-router";

// 계정 스토어
const accountStore = useAccountStore();

// 라우트 객체: VueRouter에서 제공하는 컴포지션 API 함수로, 현재 활성화된 라우트의 정보를 가져오는데 사용됨
const route = useRoute();

// 로그인 여부 확인
const checkAccount = async () => {
  const res = await check();
  if(res.status === 200){
    accountStore.setChecked(true);
    accountStore.setLoggedIn(true);
  }else {
    accountStore.setChecked(false);
  }
};

// 커스텀 생성 훅
(async function onCreated(){
  await checkAccount();
})();

// 라우트 경로가 바뀔 때마다 로그인 여부를 확인
watch(()=> route.path, () => {
  checkAccount();
});

</script>

<template>
  <template v-if="accountStore.checked">
    <Header />
    <mai>
      <router-view></router-view>
    </mai>
    <Footer />
  </template>
</template>