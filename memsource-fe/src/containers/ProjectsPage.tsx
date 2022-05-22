import ProjectsTable from "../components/ProjectsTable";
import {useEffect} from "react";
import {fetchPagedProjects} from "../api/ApiService";
import {ProjectsPageRequest} from "../domain/request/ProjectsPageRequest";

const ProjectsPage = (): JSX.Element => {

    useEffect(() => {
        fetchPagedProjects({page: 1, pageSize: 10} as ProjectsPageRequest)
            .then(res => {
            }).catch(err => {
            })
    })

    return (
        <div className="container">
            <div className="row">
                <div className="col-4">
                </div>
                <div className="col-8">
                    <ProjectsTable projects={[]}/>
                </div>
            </div>

        </div>
    )

};

export default ProjectsPage;