WITH RankedEmployees AS (
    SELECT 
        e.name AS Employee,
        e.salary AS Salary,
        e.departmentId,
        DENSE_RANK() OVER (PARTITION BY e.departmentId ORDER BY e.salary DESC) AS salary_rank
    FROM Employee e
)
SELECT 
    d.name AS Department,
    re.Employee,
    re.Salary
FROM RankedEmployees re
JOIN Department d ON re.departmentId = d.id
WHERE re.salary_rank <= 3;