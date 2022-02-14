import Layout from "../../components/layout";
import UserTableLoader from "../../components/userTableLoader";

export default function Stats() {
    return (
        <Layout admin>
            <UserTableLoader endpoint={'http://localhost:8081/admin/users'}/>
        </Layout>
    )
}