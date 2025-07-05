const URL = 'http://localhost:8080/api/examenes';
const tabla = document.getElementById('tabla');
const formulario = document.getElementById('formulario');
const buscar = document.getElementById('buscar');
const modalEditar = new bootstrap.Modal(document.getElementById('modalEditar'));
const formEditar = document.getElementById('formEditar');

document.addEventListener('DOMContentLoaded', () => {
  obtenerExamenes();
  cargarPacientes();
  cargarDoctores();
});

formulario.addEventListener('submit', guardarExamen);
formEditar.addEventListener('submit', actualizarExamen);
buscar.addEventListener('input', obtenerExamenes);

function obtenerExamenes() {
  fetch(URL)
    .then(res => res.json())
    .then(examenes => {
      tabla.innerHTML = '';
      examenes.forEach(e => {
        if (!e.paciente.toLowerCase().includes(buscar.value.toLowerCase())) return;
        tabla.innerHTML += `
          <tr>
            <td>${e.paciente}</td>
            <td>${e.doctor}</td>
            <td>${e.tipoExamen}</td>
            <td>${e.fecha}</td>
            <td>${e.resultado}</td>
            <td>
              <button class="btn btn-warning btn-sm me-1" onclick='abrirModal(${JSON.stringify(e)})'>Editar</button>
              <button class="btn btn-danger btn-sm" onclick='eliminarExamen(${e.id})'>Eliminar</button>
            </td>
          </tr>
        `;
      });
    })
    .catch(err => console.error("Error al cargar exámenes:", err));
}

function guardarExamen(e) {
  e.preventDefault();
  const examen = {
    paciente: document.getElementById('paciente').value,
    doctor: document.getElementById('doctor').value,
    tipoExamen: document.getElementById('tipo').value,
    fecha: document.getElementById('fecha').value,
    resultado: document.getElementById('resultado').value,
    observaciones: document.getElementById('observaciones').value
  };

  fetch(URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(examen)
  }).then(() => {
    formulario.reset();
    obtenerExamenes();
  }).catch(err => console.error("Error al guardar examen:", err));
}

function eliminarExamen(id) {
  if (!confirm("¿Está seguro de eliminar este examen?")) return;

  fetch(`${URL}/${id}`, { method: 'DELETE' })
    .then(() => obtenerExamenes())
    .catch(err => console.error("Error al eliminar examen:", err));
}

function abrirModal(e) {
  document.getElementById('edit-id').value = e.id;
  document.getElementById('edit-paciente').value = e.paciente;
  document.getElementById('edit-doctor').value = e.doctor;
  document.getElementById('edit-tipo').value = e.tipoExamen;
  document.getElementById('edit-fecha').value = e.fecha;
  document.getElementById('edit-resultado').value = e.resultado;
  document.getElementById('edit-observaciones').value = e.observaciones;
  modalEditar.show();
}

function actualizarExamen(e) {
  e.preventDefault();
  const id = document.getElementById('edit-id').value;
  const examen = {
    paciente: document.getElementById('edit-paciente').value,
    doctor: document.getElementById('edit-doctor').value,
    tipoExamen: document.getElementById('edit-tipo').value,
    fecha: document.getElementById('edit-fecha').value,
    resultado: document.getElementById('edit-resultado').value,
    observaciones: document.getElementById('edit-observaciones').value
  };

  fetch(`${URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(examen)
  }).then(() => {
    modalEditar.hide();
    obtenerExamenes();
  }).catch(err => console.error("Error al actualizar examen:", err));
}

function cargarPacientes() {
  fetch('http://localhost:8080/pacientes')
    .then(res => res.json())
    .then(data => {
      const select = document.getElementById('paciente');
      const selectEdit = document.getElementById('edit-paciente');
      select.innerHTML = '<option value="">Seleccione paciente</option>';
      selectEdit.innerHTML = '<option value="">Seleccione paciente</option>';
      data.forEach(p => {
        select.innerHTML += `<option value="${p.nombre}">${p.nombre}</option>`;
        selectEdit.innerHTML += `<option value="${p.nombre}">${p.nombre}</option>`;
      });
    }).catch(err => console.error("Error al cargar pacientes:", err));
}

function cargarDoctores() {
  fetch('http://localhost:8080/doctores')
    .then(res => res.json())
    .then(data => {
      const select = document.getElementById('doctor');
      const selectEdit = document.getElementById('edit-doctor');
      select.innerHTML = '<option value="">Seleccione doctor</option>';
      selectEdit.innerHTML = '<option value="">Seleccione doctor</option>';
      data.forEach(d => {
        select.innerHTML += `<option value="${d.nombre}">${d.nombre}</option>`;
        selectEdit.innerHTML += `<option value="${d.nombre}">${d.nombre}</option>`;
      });
    }).catch(err => console.error("Error al cargar doctores:", err));
}
