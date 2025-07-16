import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/Home.vue')
    },
    {
      path: '/join',
      name: 'join',
      component: () => import('../views/Join.vue')
    },
    {
      path: '/login',
      name: 'loin',
      component: () => import('../views/Login.vue')
    },
    {
      path: '/cart',
      name: 'cart',
      component: () => import('../views/Cart.vue')
    },
    {
      path: '/order',
      name: 'order',
      component: () => import('../views/OrderForm.vue')
    },
    {
      path: '/orders',
      name: 'orders',
      component: () => import('../views/Order.vue')
    },
    {
      path: '/orders/:id',
      name: 'orderDetail',
      component: () => import('../views/OrderDetail.vue')
    },
    {
      path: '/coupons',
      name: 'coupons',
      component: () => import('../views/CouponPage.vue')
    }
  ]
})

export default router
