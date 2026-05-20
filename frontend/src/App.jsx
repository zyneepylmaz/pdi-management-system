import { useEffect, useState } from "react";
import api from "./api";
import "./App.css";

import {
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
  Tooltip,
  ResponsiveContainer,
  XAxis,
  YAxis,
  CartesianGrid,
  Legend,
} from "recharts";

import {
  FiGrid,
  FiClipboard,
  FiTool,
  FiUsers,
  FiFileText,
  FiTruck,
  FiCheckCircle,
  FiXCircle,
  FiClock,
  FiRefreshCw,
} from "react-icons/fi";

function App() {
  const [activeTab, setActiveTab] = useState("dashboard");
  const [message, setMessage] = useState("");
  const [apiOnline, setApiOnline] = useState(true);

  const [customers, setCustomers] = useState([]);
  const [workOrders, setWorkOrders] = useState([]);
  const [pdiChecklists, setPdiChecklists] = useState([]);
  const [reports, setReports] = useState([]);

  useEffect(() => {
    loadAllData();
  }, []);

  const showMessage = (text) => {
    setMessage(text);
    setTimeout(() => setMessage(""), 3000);
  };

  const loadAllData = async () => {
    try {
      await Promise.all([
        loadCustomers(),
        loadWorkOrders(),
        loadPdiChecklists(),
        loadReports(),
      ]);
      setApiOnline(true);
    } catch (error) {
      console.error(error);
      setApiOnline(false);
      showMessage("API connection error.");
    }
  };

  const loadCustomers = async () => {
    const response = await api.get("/api/customers");
    setCustomers(response.data);
  };

  const loadWorkOrders = async () => {
    const response = await api.get("/api/workorders");
    setWorkOrders(response.data);
  };

  const loadPdiChecklists = async () => {
    const response = await api.get("/api/pdi-checklists");
    setPdiChecklists(response.data);
  };

  const loadReports = async () => {
    const response = await api.get("/api/reports");
    setReports(response.data);
  };

  const workOrderCreated = workOrders.filter((item) => item.status === "CREATED").length;
  const workOrderAssigned = workOrders.filter((item) => item.status === "ASSIGNED").length;
  const workOrderInProgress = workOrders.filter((item) => item.status === "IN_PROGRESS").length;
  const workOrderCompleted = workOrders.filter((item) => item.status === "COMPLETED").length;
  const workOrderCancelled = workOrders.filter((item) => item.status === "CANCELLED").length;

  const pdiCreated = pdiChecklists.filter((item) => item.status === "CREATED").length;
  const pdiInProgress = pdiChecklists.filter((item) => item.status === "IN_PROGRESS").length;
  const pdiCompleted = pdiChecklists.filter((item) => item.status === "COMPLETED").length;
  const pdiFailed = pdiChecklists.filter((item) => item.status === "FAILED").length;

  const reportsPassed = reports.filter((item) => item.status === "PASSED").length;
  const reportsFailed = reports.filter((item) => item.status === "FAILED").length;
  const reportsPending = reports.filter((item) => item.status === "PENDING_REVIEW").length;

  const averageItemsPerChecklist =
      pdiChecklists.length > 0
          ? (
              pdiChecklists.reduce((sum, item) => sum + (item.items ? item.items.length : 0), 0) /
              pdiChecklists.length
          ).toFixed(1)
          : "0.0";

  const workOrderChartData = [
    { name: "Created", value: workOrderCreated },
    { name: "Assigned", value: workOrderAssigned },
    { name: "In Progress", value: workOrderInProgress },
    { name: "Completed", value: workOrderCompleted },
    { name: "Cancelled", value: workOrderCancelled },
  ];

  const pdiPieData = [
    { name: "Completed", value: pdiCompleted },
    { name: "Failed", value: pdiFailed },
    { name: "In Progress", value: pdiInProgress },
    { name: "Created", value: pdiCreated },
  ];

  const recentChecklistRows = pdiChecklists.slice(0, 5);
  const recentWorkOrders = workOrders.slice(0, 5);

  const pieColors = ["#16a34a", "#dc2626", "#2563eb", "#f59e0b"];

  return (
      <div className="dashboard-app">
        <aside className="dashboard-sidebar">
          <div className="sidebar-brand">
            <h2>Digital PDI</h2>
            <p>PDI & Tracking System</p>
          </div>

          <nav className="sidebar-menu">
            <button
                className={activeTab === "dashboard" ? "menu-item active" : "menu-item"}
                onClick={() => setActiveTab("dashboard")}
            >
              <FiGrid /> Dashboard
            </button>

            <button
                className={activeTab === "pdi" ? "menu-item active" : "menu-item"}
                onClick={() => setActiveTab("pdi")}
            >
              <FiClipboard /> PDI Checklists
            </button>

            <button
                className={activeTab === "workorders" ? "menu-item active" : "menu-item"}
                onClick={() => setActiveTab("workorders")}
            >
              <FiTool /> Work Orders
            </button>

            <button
                className={activeTab === "reports" ? "menu-item active" : "menu-item"}
                onClick={() => setActiveTab("reports")}
            >
              <FiFileText /> Reports
            </button>

            <button
                className={activeTab === "customers" ? "menu-item active" : "menu-item"}
                onClick={() => setActiveTab("customers")}
            >
              <FiUsers /> Customers
            </button>
          </nav>
        </aside>

        <main className="dashboard-main">
          <header className="dashboard-header">
            <div>
              <h1>Digital PDI & Operations Dashboard</h1>
              <p>
                PDI processes, work orders, customer operations and report statuses
                are monitored on a single screen.
              </p>
            </div>

            <div className="header-actions">
              <div className={apiOnline ? "api-badge online" : "api-badge offline"}>
                {apiOnline ? "API Connected" : "API Offline"}
              </div>

              <button className="refresh-btn" onClick={loadAllData}>
                <FiRefreshCw /> Refresh
              </button>
            </div>
          </header>

          {message && <div className="top-message">{message}</div>}

          {activeTab === "dashboard" && (
              <>
                <section className="kpi-grid">
                  <KpiCard
                      title="Total Customers"
                      value={customers.length}
                      description="Registered company customers"
                      icon={<FiUsers />}
                      iconClass="blue"
                  />

                  <KpiCard
                      title="Open Work Orders"
                      value={workOrderCreated + workOrderAssigned + workOrderInProgress}
                      description="Pending, assigned or active work orders"
                      icon={<FiTool />}
                      iconClass="orange"
                  />

                  <KpiCard
                      title="Completed PDI"
                      value={pdiCompleted}
                      description="PDI forms completed successfully"
                      icon={<FiCheckCircle />}
                      iconClass="green"
                  />

                  <KpiCard
                      title="Failed PDI"
                      value={pdiFailed}
                      description="PDI checklists with nonconformities"
                      icon={<FiXCircle />}
                      iconClass="red"
                  />

                  <KpiCard
                      title="Reports Ready"
                      value={reportsPassed}
                      description="Approved or passed reports"
                      icon={<FiFileText />}
                      iconClass="purple"
                  />

                  <KpiCard
                      title="Pending Reports"
                      value={reportsPending}
                      description="Reports waiting for final review"
                      icon={<FiClock />}
                      iconClass="yellow"
                  />

                  <KpiCard
                      title="Vehicles in PDI"
                      value={pdiCreated + pdiInProgress}
                      description="Currently under checklist process"
                      icon={<FiTruck />}
                      iconClass="cyan"
                  />

                  <KpiCard
                      title="Avg. Checklist Items"
                      value={averageItemsPerChecklist}
                      description="Average control items per checklist"
                      icon={<FiClipboard />}
                      iconClass="dark"
                  />
                </section>

                <section className="chart-grid">
                  <div className="panel-card large">
                    <div className="panel-header">
                      <div>
                        <h3>Work Order Status Summary</h3>
                        <p>Distribution of all work orders by status</p>
                      </div>
                    </div>

                    <div className="chart-area">
                      <ResponsiveContainer width="100%" height={320}>
                        <BarChart data={workOrderChartData}>
                          <CartesianGrid strokeDasharray="3 3" vertical={false} />
                          <XAxis dataKey="name" />
                          <YAxis allowDecimals={false} />
                          <Tooltip />
                          <Bar dataKey="value" fill="#2563eb" radius={[8, 8, 0, 0]} />
                        </BarChart>
                      </ResponsiveContainer>
                    </div>
                  </div>

                  <div className="panel-card">
                    <div className="panel-header">
                      <div>
                        <h3>PDI Status Distribution</h3>
                        <p>Completed, failed and active PDI forms</p>
                      </div>
                    </div>

                    <div className="chart-area">
                      <ResponsiveContainer width="100%" height={320}>
                        <PieChart>
                          <Pie
                              data={pdiPieData}
                              dataKey="value"
                              nameKey="name"
                              outerRadius={100}
                              innerRadius={55}
                              paddingAngle={3}
                              label
                          >
                            {pdiPieData.map((entry, index) => (
                                <Cell
                                    key={entry.name}
                                    fill={pieColors[index % pieColors.length]}
                                />
                            ))}
                          </Pie>
                          <Tooltip />
                          <Legend />
                        </PieChart>
                      </ResponsiveContainer>
                    </div>
                  </div>
                </section>

                <section className="bottom-grid">
                  <div className="panel-card">
                    <div className="panel-header">
                      <div>
                        <h3>Recent Work Orders</h3>
                        <p>Latest tracked work orders</p>
                      </div>
                    </div>

                    <div className="simple-table">
                      <table>
                        <thead>
                        <tr>
                          <th>ID</th>
                          <th>Code</th>
                          <th>Plate</th>
                          <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        {recentWorkOrders.length === 0 ? (
                            <tr>
                              <td colSpan="4" className="empty-row">
                                No work orders found.
                              </td>
                            </tr>
                        ) : (
                            recentWorkOrders.map((item) => (
                                <tr key={item.id}>
                                  <td>{item.id}</td>
                                  <td>{item.workOrderCode}</td>
                                  <td>{item.vehiclePlate}</td>
                                  <td>
                              <span className={`status-pill ${mapStatusClass(item.status)}`}>
                                {item.status}
                              </span>
                                  </td>
                                </tr>
                            ))
                        )}
                        </tbody>
                      </table>
                    </div>
                  </div>

                  <div className="panel-card">
                    <div className="panel-header">
                      <div>
                        <h3>Recent PDI Checklists</h3>
                        <p>Latest inspection records</p>
                      </div>
                    </div>

                    <div className="simple-table">
                      <table>
                        <thead>
                        <tr>
                          <th>ID</th>
                          <th>Plate</th>
                          <th>Type</th>
                          <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        {recentChecklistRows.length === 0 ? (
                            <tr>
                              <td colSpan="4" className="empty-row">
                                No PDI checklists found.
                              </td>
                            </tr>
                        ) : (
                            recentChecklistRows.map((item) => (
                                <tr key={item.id}>
                                  <td>{item.id}</td>
                                  <td>{item.vehiclePlate}</td>
                                  <td>{item.inspectionType}</td>
                                  <td>
                              <span className={`status-pill ${mapStatusClass(item.status)}`}>
                                {item.status}
                              </span>
                                  </td>
                                </tr>
                            ))
                        )}
                        </tbody>
                      </table>
                    </div>
                  </div>
                </section>
              </>
          )}

          {activeTab === "customers" && (
              <SimpleListSection
                  title="Customers"
                  subtitle="Registered customer companies"
                  columns={["ID", "Company", "Sector", "Contact", "Email"]}
                  rows={customers.map((item) => [
                    item.id,
                    item.companyName,
                    item.sector,
                    item.contactPerson,
                    item.contactEmail,
                  ])}
              />
          )}

          {activeTab === "workorders" && (
              <SimpleListSection
                  title="Work Orders"
                  subtitle="Tracked work order records"
                  columns={["ID", "Code", "Plate", "Type", "Priority", "Status"]}
                  rows={workOrders.map((item) => [
                    item.id,
                    item.workOrderCode,
                    item.vehiclePlate,
                    item.inspectionType,
                    item.priority,
                    item.status,
                  ])}
              />
          )}

          {activeTab === "pdi" && (
              <SimpleListSection
                  title="PDI Checklists"
                  subtitle="Inspection checklist records"
                  columns={["ID", "Customer ID", "Plate", "Model", "Type", "Status"]}
                  rows={pdiChecklists.map((item) => [
                    item.id,
                    item.customerId,
                    item.vehiclePlate,
                    item.vehicleModel,
                    item.inspectionType,
                    item.status,
                  ])}
              />
          )}

          {activeTab === "reports" && (
              <SimpleListSection
                  title="Reports"
                  subtitle="Generated report results"
                  columns={["ID", "Checklist ID", "Customer ID", "Plate", "Status", "Generated By"]}
                  rows={reports.map((item) => [
                    item.id,
                    item.pdiChecklistId,
                    item.customerId,
                    item.vehiclePlate,
                    item.status,
                    item.generatedBy,
                  ])}
              />
          )}
        </main>
      </div>
  );
}

function KpiCard({ title, value, description, icon, iconClass }) {
  return (
      <div className="kpi-card">
        <div className="kpi-top">
          <div>
            <span className="kpi-title">{title}</span>
            <h3>{value}</h3>
          </div>
          <div className={`kpi-icon ${iconClass}`}>{icon}</div>
        </div>
        <p>{description}</p>
      </div>
  );
}

function SimpleListSection({ title, subtitle, columns, rows }) {
  return (
      <section className="panel-card">
        <div className="panel-header">
          <div>
            <h3>{title}</h3>
            <p>{subtitle}</p>
          </div>
        </div>

        <div className="simple-table">
          <table>
            <thead>
            <tr>
              {columns.map((column) => (
                  <th key={column}>{column}</th>
              ))}
            </tr>
            </thead>
            <tbody>
            {rows.length === 0 ? (
                <tr>
                  <td colSpan={columns.length} className="empty-row">
                    No data found.
                  </td>
                </tr>
            ) : (
                rows.map((row, index) => (
                    <tr key={index}>
                      {row.map((cell, cellIndex) => (
                          <td key={cellIndex}>{cell || "-"}</td>
                      ))}
                    </tr>
                ))
            )}
            </tbody>
          </table>
        </div>
      </section>
  );
}

function mapStatusClass(status) {
  if (!status) return "default";
  const value = status.toUpperCase();

  if (value === "COMPLETED" || value === "PASSED") return "success";
  if (value === "FAILED" || value === "CANCELLED") return "danger";
  if (value === "IN_PROGRESS" || value === "ASSIGNED" || value === "PENDING_REVIEW")
    return "info";
  if (value === "CREATED") return "warning";

  return "default";
}

export default App;