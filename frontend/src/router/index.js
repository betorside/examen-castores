import { createRouter, createWebHistory } from 'vue-router'
import InventarioView from '../views/InventarioView.vue'
import TransaccionesView from '../views/TransaccionesView.vue'
import LoginView from '../views/LoginView.vue'
import AlmacenView from '../views/AlmacenView.vue'

const routes = [
  { path: '/', component: LoginView },
  { path: '/inventario', component: InventarioView, meta: { requiresAuth: true } },
  { path: '/transacciones', component: TransaccionesView, meta: { requiresAuth: true } },
  { path: '/almacen', component: AlmacenView, meta: { requiresAuth: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Validación de token antes de cada ruta protegida
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.meta.requiresAuth && !token) {
    next({ path: '/' })
  } else {
    next()
  }
})

export default router