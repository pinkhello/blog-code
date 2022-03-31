package biz

import (
	"context"
	"ent-demo/gen/entschema"
	"ent-demo/gen/entschema/user"
	"fmt"
	"log"
)

func CreateUser(ctx context.Context, client *entschema.Client) (*entschema.User, error) {
	u, err := client.User.
		Create().
		SetAge(30).
		SetName("wocao404").
		Save(ctx)
	if err != nil {
		return nil, fmt.Errorf("failed creating user: %w", err)
	}
	log.Println("user created: ", u)
	return u, nil
}

func QueryUser(ctx context.Context, client *entschema.Client) (*entschema.User, error) {
	u, err := client.User.
		Query().Where(user.Name("wocao404")).Only(ctx)
	if err != nil {
		return nil, fmt.Errorf("failed querying user: %w", err)
	}
	log.Println("user returned:", u)
	return u, nil
}
