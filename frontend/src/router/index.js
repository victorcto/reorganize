import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: function (){
      return import('../views/Login.vue');
    }
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/tasks',
    name: 'tasks',
    component: function (){
      return import('../views/Tasks.vue');
    }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
