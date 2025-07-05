const apiUrl = 'http://localhost:8080/api/medicamentos';
const tabla = document.getElementById('tabla');
const formulario = document.getElementById('formulario');
const buscar = document.getElementById('buscar');

let medicamentos = [];

function renderizarTabla() {
  const filtro = buscar.value.toLowerCase();
  tabla.innerHTML = medicamentos
    .filter(m => m.nombre.toLowerCase().includes(filtro))
    .map(m => `
      <tr>
        <td>${m.nombre}</td>
        <td>${m.dosis}</td>
        <td>${m.indicaciones}</td>
        <td>${m.vencimiento}</td>
        <td>
          <button class="btn btn-warning btn-sm" onclick="editar(${m.id})">Editar</button>
          <button class="btn btn-danger btn-sm" onclick="eliminar(${m.id})">Eliminar</button>
        </td>
      </tr>
    `).join('');
}

formulario.onsubmit = e => {
  e.preventDefault();
  const nuevo = {
    nombre: document.getElementById('nombre').value,
    dosis: document.getElementById('dosis').value,
    indicaciones: document.getElementById('indicaciones').value,
    vencimiento: document.getElementById('vencimiento').value,
  };
  fetch(apiUrl, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(nuevo)
  }).then(cargarMedicamentos);
  formulario.reset();
};

function editar(id) {
  const m = medicamentos.find(x => x.id === id);
  document.getElementById('edit-id').value = m.id;
  document.getElementById('edit-nombre').value = m.nombre;
  document.getElementById('edit-dosis').value = m.dosis;
  document.getElementById('edit-indicaciones').value = m.indicaciones;
  document.getElementById('edit-vencimiento').value = m.vencimiento;
  new bootstrap.Modal(document.getElementById('modalEditar')).show();
}

document.getElementById('formEditar').onsubmit = e => {
  e.preventDefault();
  const id = document.getElementById('edit-id').value;
  const actualizado = {
    nombre: document.getElementById('edit-nombre').value,
    dosis: document.getElementById('edit-dosis').value,
    indicaciones: document.getElementById('edit-indicaciones').value,
    vencimiento: document.getElementById('edit-vencimiento').value,
  };
  fetch(`${apiUrl}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(actualizado)
  }).then(cargarMedicamentos);
  bootstrap.Modal.getInstance(document.getElementById('modalEditar')).hide();
};

function eliminar(id) {
  if (confirm('¿Seguro que deseas eliminar este medicamento?')) {
    fetch(`${apiUrl}/${id}`, { method: 'DELETE' }).then(cargarMedicamentos);
  }
}

function cargarMedicamentos() {
  fetch(apiUrl)
    .then(r => r.json())
    .then(data => {
      medicamentos = data;
      renderizarTabla();
    });
}

buscar.oninput = renderizarTabla;

cargarMedicamentos();
