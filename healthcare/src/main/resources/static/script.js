const API_URL = '/api/patients';

async function loadPatients() {
    try {
        const response = await fetch(API_URL);
        const patients = await response.json();
        const tableBody = document.querySelector('#patientsTable tbody');
        tableBody.innerHTML = '';

        patients.forEach(p => {
            const row = `
                <tr>
                    <td>${p.patient_full_name}</td>
                    <td>${p.doctor_name}</td>
                    <td>${p.hospital_name}</td>
                    <td>
                        <button onclick="deletePatient(${p.patient_id})" class="delete-btn">Delete</button>
                    </td>
                </tr>
            `;
            tableBody.insertAdjacentHTML('beforeend', row);
        });
    } catch (e) { console.error(e); }
}

document.getElementById('patientForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const patientData = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        hospitalId: parseInt(document.getElementById('hospitalId').value),
        dob: document.getElementById('dob').value,
        condition: document.getElementById('condition').value
    };

    const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(patientData)
    });
    if (response.ok) {
        document.getElementById('patientForm').reset();
        setTimeout(loadPatients, 500);
    } else {
        alert("Ошибка! Проверьте, что Hospital ID существует в базе.");
    }
});

async function deletePatient(id) {
    if (confirm('Delete?')) {
        await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        loadPatients();
    }
}

document.addEventListener('DOMContentLoaded', loadPatients);