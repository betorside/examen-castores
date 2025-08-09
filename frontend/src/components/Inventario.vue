<template>
  <div class="inventario-container">
    <h2>Inventario</h2>
    <button v-if="rol === 'Administrador'" @click="showAgregar = true">Agregar Producto</button>
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
            <button v-if="rol === 'Administrador' && prod.estatus === 1" @click="abrirEntrada(prod)">Entrada</button>
            <button v-if="rol === 'Administrador' && prod.estatus === 0" @click="activar(prod)">Dar de alta</button>
            <button v-if="rol === 'Administrador' && prod.estatus === 1" @click="inactivar(prod)">Inactivar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Agregar producto -->
    <div v-if="showAgregar && rol === 'Administrador'" class="modal">
      <div class="modal-content">
        <h3>Agregar Producto</h3>
        <label>Nombre:</label>
        <input v-model="nuevoProducto.nombre" placeholder="Nombre" />
        <label>Precio:</label>
        <input v-model="nuevoProducto.precio" type="number" placeholder="Precio" />
        <label>Descripción:</label>
        <input v-model="nuevoProducto.descripcion" placeholder="Descripción" />
        <button @click="agregarProducto">Guardar</button>
        <button @click="showAgregar = false">Cancelar</button>
      </div>
    </div>

    <!-- Entrada producto -->
    <div v-if="showEntrada && rol === 'Administrador'" class="modal">
      <div class="modal-content">
        <h3>Entrada de Producto</h3>
        <label>Cantidad:</label>
        <input v-model="cantidadEntrada" type="number" placeholder="Cantidad" />
        <button @click="entradaProducto">Aceptar</button>
        <button @click="showEntrada = false">Cancelar</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const API_URL = 'http://localhost:8080'

const productos = ref([])
const showAgregar = ref(false)
const showEntrada = ref(false)
const nuevoProducto = ref({ nombre: '', precio: 0, descripcion: '' })
const productoActual = ref(null)
const cantidadEntrada = ref(0)
const token = localStorage.getItem('token')
const rol = localStorage.getItem('rol')

const cargarProductos = async () => {
  const res = await axios.get(`${API_URL}/productos`, {
    headers: { Authorization: `Bearer ${token}` }
  })
  productos.value = res.data
}

onMounted(cargarProductos)

const agregarProducto = async () => {
  await axios.post(`${API_URL}/productos`, nuevoProducto.value, {
    headers: { Authorization: `Bearer ${token}` }
  })
  showAgregar.value = false
  cargarProductos()
}

const abrirEntrada = (item) => {
  productoActual.value = item
  cantidadEntrada.value = 0
  showEntrada.value = true
}

const entradaProducto = async () => {
  await axios.put(`${API_URL}/productos/entrada/${productoActual.value.idProducto}`, { cantidad: cantidadEntrada.value }, {
    headers: { Authorization: `Bearer ${token}` }
  })
  showEntrada.value = false
  cargarProductos()
}

const activar = async (item) => {
  await axios.put(`${API_URL}/productos/activar/${item.idProducto}`, {}, {
    headers: { Authorization: `Bearer ${token}` }
  })
  cargarProductos()
}

const inactivar = async (item) => {
  await axios.put(`${API_URL}/productos/baja/${item.idProducto}`, {}, {
    headers: { Authorization: `Bearer ${token}` }
  })
  cargarProductos()
}
</script>

<style scoped>
.inventario-container { max-width: 900px; margin: 40px auto; }
table { width: 100%; border-collapse: collapse; margin-top: 20px; }
th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
button { margin: 2px; padding: 6px 12px; }
.modal { position: fixed; top: 0; left: 0; width: 100vw; height: 100vh; background: rgba(0,0,0,0.3); display: flex; align-items: center; justify-content: center; }
.modal-content { background: #fff; padding: 20px; border-radius: 8px; min-width: 300px; }


.modal-content label {
  display: block;
  margin-top: 10px;
  margin-bottom: 5px;
  font-weight: bold;
}

.modal-content input {
  width: 100%;
  margin-bottom: 10px;
  padding: 5px;
}
</style>
