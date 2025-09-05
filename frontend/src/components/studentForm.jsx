import { useMutation, QueryClient } from '@tanstack/react-query';
import { API_URL } from "../App";
import { useState } from 'react';

function StudentForm() {
    const [formData, setFormData] = useState({
        name: '',
        age: 0
    });

    // Data ophalen...
    const studentServerState = useMutation({
        mutationFn: async (studentData) => {
            const response = await fetch(`${API_URL}/students`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(studentData)
            });

            if (!response.ok) {
                throw new Error("Er is iets fout gegaan :(");
            }

            return response.json();
        },
        onSuccess: () => {
            console.log("Gelukt!");
            setFormData({ name: '', age: '' })
        }
    });

    console.log("Is het pending?", studentServerState.isPending);
    console.log("Is het error?", studentServerState.isError);

    // createStudent.mutate()

    // // Custom function
    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(formData);
        studentServerState.mutate(formData);
    };

    // Custom function
    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value
        })
    }

    const isSubmitDisabled = () => {
        // Met Object.values halen we all values op
        // { key: value}
        for (const value of Object.values(formData)) {
            if (value == '') {
                return true;
            }
        }

        return false;
    }

    return (
        <form onSubmit={handleSubmit}>
            {studentServerState.error && (
                <div style={{ color: 'red', marginBottom: '10px' }}>
                    Error: Er is iets fout gegaan
                </div>
            )}
            <div>
                <label htmlFor="name">Student Name:</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    disabled={studentServerState.isPending}
                />
            </div>
            <div>
                <label htmlFor="age">Age:</label>
                <input
                    type="text"
                    id="age"
                    name="age"
                    value={formData.age}
                    onChange={handleChange}
                />
            </div>
            <button
                type="submit"
                // disabled={isSubmitDisabled}
                disabled={isSubmitDisabled()}
            >Add Student</button>
        </form>
    );
}

export default StudentForm;