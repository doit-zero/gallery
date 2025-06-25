<script setup>
import {useAccountStore} from "@/stores/account.js";
import {logout} from "@/services/accountService.js";
import {useRoute, useRouter} from "vue-router";

// 계정 스토어
const accountStore = useAccountStore();

// 라우터 객체
const router = useRouter();

// 로그아웃
const logoutAccount = async () => {
  const res = await logout();

  if(res.status === 200){
    accountStore.setAccessToken(""); // 로그아웃 성공 시 계정 스토어의 액세스 토큰 값을 초기화
    accountStore.setLoggedIn(false);
    await router.push("/"); // 로그아웃 성공시 메인 화면으로 이동
  }
};

</script>

<template>
  <header>
    <div class="navbar navbar-dark bg-dark text-white shadow-sm">
      <router-link to="/" class="navbar-brand">
        <strong>Gallery</strong>
      </router-link>
      <div class="menus d-flex gap-3">
        <template v-if="!accountStore.loggedIn">
          <router-link to="/login" class="navbar-brand">로그인</router-link>
          <router-link to="/join" class="navbar-brand">회원가입</router-link>
        </template>
        <template v-else>
          <a @click="logoutAccount">로그아웃</a>
          <router-link to="/orders">주문 내역</router-link>
          <router-link to="/cart">장바구니</router-link>
        </template>
      </div>
    </div>
  </header>
</template>

<style lang="scss">
header{
  .menus{
    a {
      cursor: pointer;
      color: #fff;
      text-decoration: none;
    }
  }
}
</style>