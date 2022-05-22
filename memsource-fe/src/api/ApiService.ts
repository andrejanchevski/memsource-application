import {axiosClient} from "./ApiClient";
import {LogInUserRequest} from "../domain/request/LogInUserRequest";
import {ProjectsPageRequest} from "../domain/request/ProjectsPageRequest";

export const logInUser = (logInUserRequest: LogInUserRequest) => {
    return axiosClient.post(`/login?username=${logInUserRequest.username}&password=${logInUserRequest.password}`,
        {},
        {headers: {'content-type' : 'application/x-www-form-urlencoded'}})
}

export const fetchPagedProjects = (projectsPagedRequest: ProjectsPageRequest) => {
    return axiosClient.get(`projects/all?pageSize=${projectsPagedRequest.pageSize}&page=${projectsPagedRequest}`)
}