package com.bank.rest;

import com.bank.dao.BankDAO;
import com.bank.model.Student;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/students")
public class StudentResource {

	@Inject
	private BankDAO bankDAO;
	
	//GET /api/students
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents(){
		return bankDAO.getAllStudents();
	}
	
	//GET /api/students/{student_id}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") int id) {
        Student student = bankDAO.findStudentByID(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(student).build();
    }
	// POST /api/students
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) {
        Student newStudent = bankDAO.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(newStudent).build();
    }

 // PUT /api/students/{id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") int id, Student updatedStudent) {
        Student existingStudent = bankDAO.findStudentByID(id);
        if (existingStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingStudent.setStudentName(updatedStudent.getStudentName());
        // TODO: Implement PUT logic for all fields
        Student merged = bankDAO.updateStudent(existingStudent);
        return Response.ok(merged).build();
    }
    
 // DELETE /api/students/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") int id) {
        boolean removed = bankDAO.deleteStudent(id);
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
