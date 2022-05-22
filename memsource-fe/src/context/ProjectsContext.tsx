import {createContext, useContext, useState} from "react";
import {ContextChildrenPropType} from "../domain/ContextChildrenPropType";
import {PagedProjectsResponse} from "../domain/response/PagedProjectsResponse";
import {PagedProjectsDTO} from "../domain/PagedProjectsDTO";

const PagedProjectsContext = createContext({});
const PagedProjectsUpdateContext = createContext({});

export const usePagedProjectsContext = () => {
    const context = useContext(PagedProjectsContext);
    if (context === undefined) {
        throw new Error("usePagedProjectsContext was outside of its provider");
    }

    return context;
}

export const usePagedProjectsUpdateContext = () => {
    const context = useContext(PagedProjectsUpdateContext);
    if (context === undefined) {
        throw new Error("usePagedProjectsContext was outside of its provider")
    }
    return context;
}

export const PagedProjectsContextProvider = ({children}: ContextChildrenPropType) => {
    const [currentPagedProjects, setCurrentPagedProjects] = useState<PagedProjectsDTO>({
        page: 1,
        pageSize: 10,
        projects: [],
        totalPages: 1
    })

    const setPagedProjects = (pagedProjects: PagedProjectsResponse, currentPage: number) => {
        setCurrentPagedProjects(current => (
            {
                pageSize: pagedProjects.size,
                page: currentPage,
                projects: pagedProjects.content,
                totalPages: pagedProjects.totalPages
            }
        ))
    }

    return (<PagedProjectsContext.Provider value={currentPagedProjects}>
        <PagedProjectsUpdateContext.Provider value={setPagedProjects}>
            {children}
        </PagedProjectsUpdateContext.Provider>
    </PagedProjectsContext.Provider>)
}