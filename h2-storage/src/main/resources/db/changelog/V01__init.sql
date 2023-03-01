
CREATE TABLE public.llabel (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 4) PRIMARY KEY,
	name varchar(22) NULL unique,
	description varchar(100) NULL,
	classifier_data varchar(200) NULL,
	technical bool NOT NULL DEFAULT false,
	parent int8 NULL,
	CONSTRAINT llabel_pkey PRIMARY KEY (id),
	CONSTRAINT name_unique UNIQUE (name),
	CONSTRAINT llabel_parent_fkey FOREIGN KEY (parent) REFERENCES public.llabel(id)
);
