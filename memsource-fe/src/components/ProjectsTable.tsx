import {ProjectResponse} from "../domain/response/ProjectResponse";

interface ProjectTableProps{
    projects: ProjectResponse[]
}

const ProjectsTable = (props: ProjectTableProps) => {
    return (<div>
        <table className="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Project Name</th>
                <th scope="col">Project Status</th>
                <th scope="col">Source Language</th>
                <th scope="col">Target Languages</th>
                <th scope="col">Date Created</th>
            </tr>
            </thead>
            <tbody>
            {props.projects.map((project,idx) => {
                return <tr>
                    <td>{idx}</td>
                    <td>
                        {project.projectName}
                    </td>
                    <td>
                        {project.projectStatus}
                    </td>
                    <td>
                        {project.sourceLanguage}
                    </td>
                    <td>
                        View Target Languages
                    </td>
                    <td>
                        {project.dateCreated}
                    </td>
                </tr>
            })}
            </tbody>
        </table>
    </div>)
}

export default ProjectsTable;