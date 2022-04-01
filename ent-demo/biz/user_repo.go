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

func TxCreateUser(ctx context.Context, client *entschema.Client) error {
	tx, err := client.Tx(ctx)
	if err != nil {
		return fmt.Errorf("starting a transaction: %w", err)
	}
	u, err := tx.User.
		Create().
		SetAge(10).
		SetName("cxq").
		Save(ctx)
	if err != nil {
		return rollback(tx, err)
	}
	fmt.Println("create user cxq success", u)
	i, err := tx.User.Delete().Where(user.Name("cxq")).Exec(ctx)
	if err != nil {
		return rollback(tx, err)
	}
	fmt.Println("delete user cxq success", i)
	return tx.Commit()
}

func rollback(tx *entschema.Tx, err error) error {
	if rerr := tx.Rollback(); rerr != nil {
		err = fmt.Errorf("%w: %v", err, rerr)
	}
	return err
}
