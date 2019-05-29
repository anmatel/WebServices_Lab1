/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices_lab3.Server;

import java.util.List;
import java.util.concurrent.Future;
import javax.xml.ws.AsyncHandler;
import webservices_lab3.Client.CreatePersonResponse;
import webservices_lab3.Client.DeletePersonResponse;
import webservices_lab3.Client.GetPersonsResponse;
import webservices_lab3.Client.IllegalEnteredDataException;
import webservices_lab3.Client.Person;
import webservices_lab3.Client.PostgreSQLDAO;
import webservices_lab3.Client.UpdatePersonResponse;
import webservices_lab3.Errors.ThrottlingException;
import webservices_lab3.Errors.ThrottlingFault;

/**
 *
 * @author Анастасия
 */
public class PersonWebServiceAsync implements IPersonWebServiceAsync {

    private static final int MAX_REQUESTS = 3;
    private static int curRequestsNumber;

    @Override
    public Future<?> getPersonsAsync(Person arg0, PostgreSQLDAO arg1, AsyncHandler<GetPersonsResponse> asyncHandler) throws IllegalEnteredDataException, ThrottlingException {
        if (curRequestsNumber >= MAX_REQUESTS) {
            throw new ThrottlingException("Throttling exception.", ThrottlingFault.defaultInstance());
        }
        final ServerAsyncResponse<GetPersonsResponse> asyncResponse = new ServerAsyncResponse<GetPersonsResponse>();
        curRequestsNumber += 1;
        new Thread() {
            public void run() {
                List<Person> persons = arg1.getPersons(arg0);
                asyncResponse.set(persons);
                asyncHandler.handleResponse(asyncResponse);
            }
        }.start();
        curRequestsNumber -= 1;
        return asyncResponse;
    }

    @Override
    public Future<?> updatePersonAsync(Integer arg0, Person arg1, PostgreSQLDAO arg2, AsyncHandler<UpdatePersonResponse> asyncHandler) throws IllegalEnteredDataException, ThrottlingException {
        if (curRequestsNumber >= MAX_REQUESTS) {
            throw new ThrottlingException("Throttling exception.", ThrottlingFault.defaultInstance());
        }
        final ServerAsyncResponse<UpdatePersonResponse> asyncResponse = new ServerAsyncResponse<UpdatePersonResponse>();
        new Thread() {
            public void run() {
                String response = arg2.updatePerson(arg0, arg1);
                asyncResponse.set(response);
                asyncHandler.handleResponse(asyncResponse);
            }
        }.start();
        curRequestsNumber -= 1;
        return asyncResponse;
    }

    @Override
    public Future<?> createPersonAsync(Person arg0, PostgreSQLDAO arg1, AsyncHandler<CreatePersonResponse> asyncHandler) throws IllegalEnteredDataException, ThrottlingException {
        if (curRequestsNumber >= MAX_REQUESTS) {
            throw new ThrottlingException("Throttling exception.", ThrottlingFault.defaultInstance());
        }
        final ServerAsyncResponse<CreatePersonResponse> asyncResponse = new ServerAsyncResponse<CreatePersonResponse>();
        new Thread() {
            public void run() {
                int personId = arg1.createPerson(arg0);
                asyncResponse.set(personId);
                asyncHandler.handleResponse(asyncResponse);
            }
        }.start();
        curRequestsNumber -= 1;
        return asyncResponse;
    }

    @Override
    public Future<?> deletePersonAsync(Integer arg0, PostgreSQLDAO arg1, AsyncHandler<DeletePersonResponse> asyncHandler) throws IllegalEnteredDataException, ThrottlingException {
        if (curRequestsNumber >= MAX_REQUESTS) {
            throw new ThrottlingException("Throttling exception.", ThrottlingFault.defaultInstance());
        }
        final ServerAsyncResponse<DeletePersonResponse> asyncResponse = new ServerAsyncResponse<DeletePersonResponse>();
        new Thread() {
            public void run() {
                String response = arg1.deletePerson(arg0);
                asyncResponse.set(response);
                asyncHandler.handleResponse(asyncResponse);
            }
        }.start();
        curRequestsNumber -= 1;
        return asyncResponse;
    }
}
