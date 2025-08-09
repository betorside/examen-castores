<template>
  <div class="almacen-container">
    <h2>Almacén</h2>
    <table>
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Precio</th>
          <th>Descripción</th>
          <th>Cantidad</th>
          <th>Estatus</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="prod in productos" :key="prod.idProducto">
          <td>{{ prod.nombre }}</td>
          <td>{{ prod.precio }}</td>
          <td>{{ prod.descripcion }}</td>
          <td>{{ prod.cantidad }}</td>
          <td>{{ prod.estatus === 1 ? 'Activo' : 'Inactivo' }}</td>
          <td>
            <button
              @click="abrirSalida(prod)"
              v-if="prod.estatus === 1 && prod.cantidad > 0"
            >
              Salida
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Salida producto -->
    <div v-if="showSalida" class="modal">
      <div class="modal-content">
        <h3>Salida de Producto</h3>
        <input v-model="cantidadSalida" type="number" placeholder="Cantidad" />
        <button @click="salidaProducto">Aceptar</button>
        <button @click="showSalida = false">Cancelar</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const API_URL = 'http://localhost:8080'

const productos = ref([])
const showSalida = ref(false)
const productoActual = ref(null)
const cantidadSalida = ref(0)
const token = localStorage.getItem('token')

const cargarProductos = async () => {
  const res = await axios.get(`${API_URL}/productos/activos`, {
    headers: { Authorization: `Bearer ${token}` }
  })
  productos.value = res.data
}

onMounted(cargarProductos)

const abrirSalida = (item) => {
  productoActual.value = item
  cantidadSalida.value = 0
  showSalida.value = true
}

const salidaProducto = async () => {
  try {
    await axios.put(`${API_URL}/productos/salida/${productoActual.value.idProducto}`, { cantidad: cantidadSalida.value }, {
      headers: { Authorization: `Bearer ${token}` }
    })
    showSalida.value = false
    cargarProductos()
  } catch (e) {
    alert(e.response.data.message || 'Error al realizar la salida de producto')
  }
}
</script>

<style scoped>
.almacen-container { max-width: 900px; margin: 40px auto; }
table { width: 100%; border-collapse: collapse; margin-top: 20px; }
th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
button { margin: 2px; padding: 6px 12px; }
.modal { position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; background: rgba(0,0,0,0.3); display: flex; align-items: center; justify-content: center; }
.modal-content { background: #fff; padding: 20px; border-radius: 8px; min-width: 300px; }
</style>