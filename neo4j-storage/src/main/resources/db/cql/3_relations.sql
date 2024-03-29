MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="db-migrator" AND b.name="database" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="junit" AND b.name="testing" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="jms" AND b.name="javaee" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="jpa" AND b.name="javaee" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="weblogic" AND b.name="javaee" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="wildfly" AND b.name="javaee" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="hibernate" AND b.name="jpa" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="oracle-db" AND b.name="database" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="mongo" AND b.name="database" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="kafka" AND b.name="mq" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="javaee" AND b.name="backend" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="angular" AND b.name="frontend" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="nodejs" AND b.name="javascript" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="linux" AND b.name="unix" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="sql" AND b.name="database" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="postgres" AND b.name="database" CREATE (a)-[r:child_of]->(b);
MATCH (a:LLABEL), (b:LLABEL) WHERE a.name="react" AND b.name="frontend" CREATE (a)-[r:child_of]->(b);
