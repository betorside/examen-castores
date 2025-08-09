<script setup>
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()
const token = localStorage.getItem('token')
const rol = localStorage.getItem('rol')

const logout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('rol')
  router.push('/')
}
</script>

<template>
  <div>
    <nav v-if="route.path !== '/'" style="background: #333; color: #fff; padding: 10px; display: flex; align-items: center;">
      <div>
        <button @click="router.push('/inventario')" style="margin-right: 10px;" v-if="token">Inventario</button>
        <button @click="router.push('/almacen')" style="margin-right: 10px;" v-if="token && rol === 'Almacenista'">Almacén</button>
        <button @click="router.push('/transacciones')" style="margin-right: 10px;" v-if="token && rol === 'Administrador'">Transacciones</button>
      </div>
      <div style="flex: 1;"></div>
      <button @click="logout" v-if="token">Cerrar sesión</button>
    </nav>
    <router-view />
  </div>
</template>
