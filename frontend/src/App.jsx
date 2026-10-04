import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [productos, setProductos] = useState([])
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch('/api/productos')
      .then((res) => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`)
        return res.json()
      })
      .then(setProductos)
      .catch((err) => setError(err.message))
  }, [])

  return (
    <main>
      <h1>Inventario del Restaurante</h1>
      {error && <p className="error">No se pudo conectar con el backend: {error}</p>}
      {!error && productos.length === 0 && <p>No hay productos registrados.</p>}
      <ul>
        {productos.map((p) => (
          <li key={p.id}>
            {p.nombre} — {p.cantidad} {p.unidad}
          </li>
        ))}
      </ul>
    </main>
  )
}

export default App
