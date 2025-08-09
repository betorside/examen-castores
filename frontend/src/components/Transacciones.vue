<template>
  <div class="transacciones-container">
    <h2>Historial de Transacciones</h2>
    <select v-model="tipo">
      <option value="">Todos</option>
      <option value="entrada">Entrada</option>
      <option value="salida">Salida</option>
    </select>
    <table>
      <thead>
        <tr>
          <th>Usuario</th>
          <th>Producto</th>
          <th>Fecha</th>
          <th>Tipo</th>
          <th>Cantidad</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="trans in transacciones" :key="trans.id">
          <td>{{ trans.usuario }}</td>
          <td>{{ trans.producto }}</td>
          <td>{{ trans.fecha }}</td>
          <td>{{ trans.tipo }}</td>
          <td>{{ trans.cantidad }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import axios from 'axios'

const transacciones = ref([])
const tipo = ref('')
const token = localStorage.getItem('token')

const cargarTransacciones = async () => {
  let url = 'http://localhost:8080/transacciones'
  if (tipo.value) url += `?tipo=${tipo.value}`
  const res = await axios.get(url, {
    headers: { Authorization: `Bearer ${token}` }
  })
  transacciones.value = res.data
}

onMounted(cargarTransacciones)
watch(tipo, cargarTransacciones)
</script>

<style scoped>
.transacciones-container { max-width: 900px; margin: 40px auto; }
table { width: 100%; border-collapse: collapse; margin-top: 20px; }
th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
select { margin-bottom: 10px; padding: 6px; }
</style>