<script setup>
import { reactive } from "vue";
import { login } from "@/services/accountService.js";
import { useRouter } from "vue-router";
import { useAccountStore } from "@/stores/account.js";

// 반응형 상태
const state = reactive({
  form: {
    loginId: "",
    loginPw: "",
  },
  errors: {
    loginId: false,
    loginPw: false
  },
  placeholders: {
    loginId: "이메일",
    loginPw: "비밀번호"
  }
});

// 라우터 객체
const router = useRouter();

// 계정 스토어
const accountStore = useAccountStore();

// 로그인 데이터 제출
const submit = async () => {
  state.errors.loginId = false;
  state.errors.loginPw = false;

  if (!state.form.loginId?.trim()) {
    state.errors.loginId = true;
    state.placeholders.loginId = "이메일을 입력해주세요";
    return;
  }

  if (!state.form.loginPw?.trim()) {
    state.errors.loginPw = true;
    state.placeholders.loginPw = "비밀번호를 입력해주세요";
    return;
  }

  const res = await login(state.form);

  switch (res.status) {
    case 200:
      accountStore.setAccessToken(res.data);
      accountStore.setLoggedIn(true);
      await router.push("/");
      break;
    case 404:
      window.alert("입력하신 정보와 일치하는 회원이 없습니다.");
      break;
  }
};
</script>

<template>
  <div class="login">
    <div class="container">
      <form class="py-5 d-flex flex-column gap-3" @submit.prevent="submit">
        <h1 class="h5 mb-3">로그인</h1>

        <!-- 이메일 입력 -->
        <div class="form-floating">
          <input
              type="email"
              class="form-control"
              :class="{ 'is-invalid': state.errors.loginId }"
              id="loginId"
              :placeholder="state.placeholders.loginId"
              v-model="state.form.loginId"
              @input="() => {
              state.errors.loginId = false;
              state.placeholders.loginId = '이메일';
            }"
          />
          <label for="loginId">이메일</label>
        </div>

        <!-- 비밀번호 입력 -->
        <div class="form-floating">
          <input
              type="password"
              class="form-control"
              :class="{ 'is-invalid': state.errors.loginPw }"
              id="loginPw"
              :placeholder="state.placeholders.loginPw"
              v-model="state.form.loginPw"
              @input="() => {
              state.errors.loginPw = false;
              state.placeholders.loginPw = '비밀번호';
            }"
          />
          <label for="loginPw">비밀번호</label>
        </div>

        <button type="submit" class="w-100 h6 btn py-3 btn-primary">로그인</button>
      </form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login > .container {
  max-width: 576px;
}

.is-invalid::placeholder {
  color: red;
}
</style>