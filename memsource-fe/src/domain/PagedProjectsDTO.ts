import {ProjectResponse} from "./response/ProjectResponse";

export interface PagedProjectsDTO{
    page: number,
    pageSize: number,
    projects: ProjectResponse[],
    totalPages: number
}