package com.bank.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import com.bank.model.Account;
import com.bank.model.Student;

import java.util.List;
import java.util.Map;

import com.bank.dao.BankDAO;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/accounts")
public class AccountResource {
	@Inject
	private BankDAO bankDAO;
	
	//GET /api/accounts
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        List<Account> accounts = bankDAO.getAllAccounts();
        return Response.ok(accounts).build();
    }
//GET /api/accounts/{account_id}
	@GET
    @Path("/{accountID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("accountID") int accountID) {

        Account account = bankDAO.getAccountByID(accountID);
        return Response.ok(account).build();
    }
	//GET /api/accounts/studentID/{student_id}
	@GET
    @Path("/studentID/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountsByStudentID(@PathParam("studentID") int studentID) {
        List<Account> accounts = bankDAO.getAccountsByStudentID(studentID);
        return Response.ok(accounts).build();
    }
	//POST /api/accounts
	@POST
    @Path("/studentID/{studentID}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account, @PathParam("studentID") int studentID) {
        Student student = bankDAO.getStudentByID(studentID);
        if (student == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Student not found").build();
        }
        account.setStudent(student);
        bankDAO.createAccount(account);
        return Response.status(Response.Status.CREATED).entity(account).build();
    }
	//PUT /api/accounts/{account_id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccount(@PathParam("id") int id, Account updatedAccount) {
    	if(bankDAO.updateAccount(updatedAccount)) {
    		return Response.status(Response.Status.CREATED).entity(updatedAccount).build();
    	}
    	
    	return Response.status(Response.Status.BAD_REQUEST).entity("Account ID" + id + " not found.").build();

    }
    // DELETE /api/accounts/{account_id}
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAccount(@PathParam("id") int id) {
        boolean removed = bankDAO.deleteStudent(id);
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
   // PUT /api/accounts/{account_ID}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyAccountBalance(@PathParam("id") int accountId, Map<String, Object> payload) {
        try {
            String operation = payload.get("operation").toString();
            float amount = Float.parseFloat(payload.get("amount").toString());

            boolean success = false;
            if ("withdraw".equalsIgnoreCase(operation)) {
                success = bankDAO.withdraw(accountId, amount);
            } else if ("deposit".equalsIgnoreCase(operation)) {
                success = bankDAO.deposit(accountId, amount);
            }

            if (success) {
                return Response.ok(Map.of("message", operation + " successful")).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity(Map.of("error", operation + " failed"))
                               .build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(Map.of("error", "Invalid input or internal error"))
                           .build();
        }
    }
    
    // PUT /api/accounts/transfer
    @PUT
    @Path("/transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferFunds(Map<String, Object> payload) {
        try {

        	//get the data first
            int fromAccountID = Integer.parseInt(payload.get("fromAccountID").toString());
            int toAccountID = Integer.parseInt(payload.get("toAccountID").toString());
            int fromStudentID = Integer.parseInt(payload.get("fromStudentID").toString());
            int toStudentID = Integer.parseInt(payload.get("toStudentID").toString());
            Account toAccount = bankDAO.getAccountByID(toAccountID);
            Account fromAccount = bankDAO.getAccountByID(fromAccountID);
            
            float amount = Float.parseFloat(payload.get("amount").toString());
            if (fromAccount.getStudent().getStudentID() != fromStudentID) {
                return Response.status(Response.Status.UNAUTHORIZED)
                               .entity(Map.of("error", "From account does not belong to the specified student"))
                               .build();
            }

            if (toAccount.getStudent().getStudentID() != toStudentID) {
                return Response.status(Response.Status.UNAUTHORIZED)
                               .entity(Map.of("error", "To account does not belong to the specified student"))
                               .build();
            }
            //do the transfer
            if (bankDAO.transfer(fromAccountID, toAccountID, amount)) {
                return Response.ok(Map.of("message", "Transfer successful")).build();
            } else {
               
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity(Map.of("error", "Transfer failed..."))
                               .build();
            }
        } catch (Exception e) {
            System.out.println("REST transfer error: " + e.getMessage()); // Debug
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(Map.of("error", "Invalid input or internal error"))
                           .build();
        }
    }

}
