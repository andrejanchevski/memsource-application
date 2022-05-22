import {PagedSortDetails} from "../PagedSortDetails";
import {ProjectResponse} from "./ProjectResponse";

export interface PagedProjectsResponse{
    last: boolean;
    totalPages: number;
    totalElements: number
    first: boolean;
    size: number
    numberOfElements: boolean
    sort: PagedSortDetails
    content: ProjectResponse[]
}