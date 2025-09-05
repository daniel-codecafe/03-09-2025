const CourseForm = () => {

    const handleSubmit = (event) => {
        event.preventDefault(); // Niet refreshen
        console.log("Formulier opgestuurd");
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="name">Course naam:</label>
                <input type="text" id="name" name="name" />
            </div>
            <div>
                <label htmlFor="maxStudents">Maximaal aantal studenten:</label>
                <input type="number" id="maxStudents" name="maxStudents" min="1" max="600" />
            </div>

            <button type="submit">Add Course</button>
        </form>
    );
}

export default CourseForm;
