import {createContext, useContext, useState} from "react";
import {ActiveUserDTO} from "../domain/ActiveUserDTO";
import {ContextChildrenPropType} from "../domain/ContextChildrenPropType";

const UserContext = createContext({});
const UseUserUpdateContext = createContext({});

export const useActiveUserContext = () => {
    const context = useContext(UserContext);
    if(context === undefined){
        throw new Error("useUserContext was outside of its provider")
    }
    return context;
}

export const useActiveUserUpdateContext: Function = () => {
    const context = useContext(UseUserUpdateContext);
    if(context === undefined){
        throw new Error("useActiveUserUpdateContext was outside of its provider")
    }

    return context;
}


export const UserContextProvider = ({children}: ContextChildrenPropType) => {
   const [currentActiveUser, setCurrentActiveUser] = useState({
       userName: "",
       authenticationToken: "",
       active: false
   })

    const setActiveUser = (activeUserDTO: ActiveUserDTO) => {
       setCurrentActiveUser(current => ({...activeUserDTO}))
    }

    return (<UserContext.Provider value={currentActiveUser}>
        <UseUserUpdateContext.Provider value={setActiveUser}>
            {children}
        </UseUserUpdateContext.Provider>
    </UserContext.Provider>)
}