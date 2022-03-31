package main

import (
	"context"
	"ent-demo/biz"
	"ent-demo/gen/entschema"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	client, err := entschema.Open("sqlite3", "file:ent?mode=memory&cache=shared&_fk=1")
	if err != nil {
		log.Fatalf("failed opening connection to sqlite: %v", err)
	}
	defer client.Close()
	if err := client.Schema.Create(context.Background()); err != nil {
		log.Fatalf("failed creating schema resoures: %v", err)
	}

	u, err := biz.CreateUser(context.Background(), client)
	if err != nil {
		log.Fatalf("create user error: %v", err)
	}
	log.Println("创建成功:", u)

	us, err := biz.QueryUser(context.Background(), client)
	if err != nil {
		log.Fatalf("query user error: %v", err)
	}
	log.Println("查询成功:", us)
}
