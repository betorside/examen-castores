<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input v-model="correo" type="email" placeholder="Correo" required />
      <input v-model="contrasena" type="password" placeholder="Contraseña" required />
      <button type="submit">Entrar</button>
    </form>
    <div v-if="error" class="error">{{ error }}</div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const correo = ref('')
const contrasena = ref('')
const error = ref('')

const login = async () => {
  try {
    const res = await axios.post('http://localhost:8080/usuarios/login', { correo: correo.value, contrasena: contrasena.value })
    localStorage.setItem('token', res.data.token)
    localStorage.setItem('rol', res.data.rol)
    window.location.href = '/inventario'
  } catch (e) {
    error.value = 'Credenciales incorrectas'
    alert(error.value)
  }
}
</script>

<style scoped>
.login-container { max-width: 400px; margin: 40px auto; padding: 20px; border: 1px solid #ccc; border-radius: 8px; }
input { display: block; width: 100%; margin-bottom: 10px; padding: 8px; }
button { width: 100%; padding: 10px; }
.error { color: red; margin-top: 10px; }
</style>